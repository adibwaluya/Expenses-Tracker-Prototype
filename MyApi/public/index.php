<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;

require __DIR__ . '/../vendor/autoload.php';

require '../includes/DbOperations.php';

$app = new \Slim\App([
    'settings'=>[
        'displayedErrorDetails'=>true
    ]
]);

/*
    API CALL
    URL for login operation
    endpoint: createuser
    parameters: email, password, name
    method: POST
*/ 
$app->post('/createuser', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('email', 'password', 'name'), $request, $response)){

        $request_data = $request->getParsedBody();

        $email = $request_data['email'];
        $password = $request_data['password'];
        $name = $request_data['name'];

        $hash_password = password_hash($password, PASSWORD_DEFAULT);
        $db = new DbOperations;

        $result = $db->createUser($email, $hash_password, $name);

        if($result == USER_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'User created successfully';

            $response->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);      // (HTTP Status code) resource created

        } else if($result == USER_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occured';

            $response->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);      // (HTTP Status code) resource created
            
        } else if($result == USER_EXISTS){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'User Already Exists';

            $response->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);      // (HTTP Status code) resource created

        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

// URL for login operation
$app->post('/userlogin', function(Request $request, Response $response){

    // request that we don't have empty email and password
    if(!haveEmptyParameters(array('email', 'password'), $request, $response)){
        $request_data = $request->getParsedBody();

        $email = $request_data['email'];
        $password = $request_data['password'];

        $db = new DbOperations;
        
        $result = $db->userLogin($email, $password);

        if($result == USER_AUTHENTICATED){
            // Get user from db by calling getUserByEmail
            $user = $db->getUserByEmail($email);

            $response_data = array();

            $response_data['error'] = false;
            $response_data['message'] = 'Login Successfull';
            $response_data['user'] = $user;

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')    
                ->withStatus(200);      // Okay

        }
        else if($result == USER_NOT_FOUND){
            $response_data = array();

            $response_data['error'] = true;
            $response_data['message'] = 'User not exist';

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')    
                ->withStatus(200);      

        }
        else if($result == USER_PASSWORD_DO_NOT_MATCH){
            $response_data = array();

            $response_data['error'] = true;
            $response_data['message'] = 'Invalid credential';

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')    
                ->withStatus(200);
            
        }
    }
    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

$app->get('/allusers', function(Request $request, Response $response){

    $db = new DbOperations;

    $users = $db->getAllUsers();

    $response_data = array();

    $response_data['error'] = false;
    $response_data['users'] = $users;

    $response->write(json_encode($response_data));

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(200);          // Okay
});

// URL for login operation
$app->put('/updateuser/{id}', function(Request $request, Response $response, array $args){

    $id = $args['id'];

    // define all the parameters that are required using an array for validation
    if(!haveEmptyParameters(array('email', 'name', 'id'), $request, $response)){

        $request_data = $request->getParsedBody();

        // data that need to be inserted in Postman
        $email = $request_data['email'];
        $name = $request_data['name'];
        $id = $request_data['id'];

        $db = new DbOperations;

        if($db->updateUser($email, $name, $id)){
            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] = 'User Updated Successfully';
            $user = $db->getUserByEmail($email);
            $response_data['user'] = $user;

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);          // Okay

        }
        else{

            // if error
            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'Please try again later';
            $user = $db->getUserByEmail($email);
            $response_data['user'] = $user;

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);          // Okay

        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(200);          // Okay
});

$app->put('/updatepassword/{id}', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('currentpassword', 'newpassword', 'email'), $request, $response)){
        
        $request_data = $request->getParsedBody();

        // data that need to be inserted in Postman
        $currentpassword = $request_data['currentpassword'];
        $newpassword = $request_data['newpassword'];
        $email = $request_data['email'];

        $db = new DbOperations;

        $result = $db->updatePassword($currentpassword, $newpassword, $email);

        if($result == PASSWORD_CHANGED){

            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] = 'Password changed';
            $response->write(json_encode($response_data));
            return $response->withHeader('Content-Type', 'application/json')
                            ->withStatus(200);
        }
        else if($result == PASSWORD_DO_NOT_MATCH){

            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'You have given wrong password';
            $response->write(json_encode($response_data));
            return $response->withHeader('Content-Type', 'application/json')
                            ->withStatus(200);
        }
        else if($result == PASSWORD_NOT_CHANGED){

            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'Some error occured';
            $response->write(json_encode($response_data));
            return $response->withHeader('Content-Type', 'application/json')
                            ->withStatus(200);
        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);          // Okay
    
});

// perform validation
function haveEmptyParameters($required_params, $request, $response) {
    $error = false;
    $error_params = '';

    $request_params = $request->getParsedBody();

    foreach($required_params as $param){
        if(!isset($request_params[$param]) || strlen($request_params[$param]) <= 0){
            $error = true;
            $error_params .= $param . ', ';
        }
    }

    if($error){
        $error_detail = array();
        $error_detail['error'] = true;
        $error_detail['message'] = 'Required parameters ' . substr($error_params, 0, -2) . ' are missing or empty';
        $response->write(json_encode($error_detail));
    }

    return $error;

}

$app->run();
