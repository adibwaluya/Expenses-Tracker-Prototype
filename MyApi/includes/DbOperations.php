<?php

    class DbOperations{

        private $con;

        function __construct(){
            require_once dirname(__FILE__)  . '/DbConnect.php';

            $db = new DbConnect;

            $this->con = $db->connect();
        }

        public function createUser($email, $password, $name) {
           if(!$this->isEmailExist($email)){
                $stmt = $this->con->prepare("INSERT INTO users (email, password, name) VALUES (?, ?, ?)");
                $stmt->bind_param("sss", $email, $password, $name);

                // Execute query
                if($stmt->execute()){
                    return USER_CREATED;

                }else{
                    return USER_FAILURE;
                }
           }
           return USER_EXISTS;
        }


        public function userLogin($email, $password){
            if($this->isEmailExist($email)){
                // if email is in db, detail of the user will be obtained
                
                // stored in db
                $hashed_password = $this->getUsersPasswordByEmail($email);

                // password in hash format will be verified
                // it cannot be compared directly using "equals to" operator
                // use this methode in PHP with two parameters:
                // (password the user has entered, hashed password)
                if(password_verify($password, $hashed_password)){

                    // Password correct
                    return USER_AUTHENTICATED;
                }
                else{
                    return USER_PASSWORD_DO_NOT_MATCH;

                }

            }
            else{
                return USER_NOT_FOUND;
            }
        }

        // Password is required to verify whether the user has entered the correct password or not
        private function getUsersPasswordByEmail($email){
            $stmt = $this->con->prepare("SELECT password FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $stmt->bind_result($password);
            $stmt->fetch();
            $user = array();
            return $password;
        }

        public function getAllUsers(){
            $stmt = $this->con->prepare("SELECT id, email, name FROM users;");
            $stmt->execute();
            $stmt->bind_result($id, $email, $name);
            $users = array();
            while($stmt->fetch()){

                // it will keep fetching the data while there is no next row to fetch 
                // so it will fetch all the users from the db
                $user = array();
                $user['id'] = $id;
                $user['email'] = $email;
                $user['name'] = $name;

                // Parameters:
                // (where the array I want to push, array that I need to push)
                // which means, pusing user into users
                array_push($users, $user);
            }
            return $users;
        }

        // when the user is authenticated, user values will be read
        public function getUserByEmail($email){
            $stmt = $this->con->prepare("SELECT id, email, name FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $stmt->bind_result($id, $email, $name);
            $stmt->fetch();                             // fetching single result
            $user = array();
            $user['id'] = $id;
            $user['email'] = $email;
            $user['name'] = $name;
            return $user;
        }

        // User can update profile information and password
        public function updateUser($email, $name, $id){
            $stmt = $this->con->prepare("UPDATE users SET email = ?, name = ? WHERE id = ?");
            $stmt->bind_param("ssi", $email, $name, $id);

            // Execute query
            if($stmt->execute()){
                return true;
            }
            return false;
        }

        public function updatePassword($currentpassword, $newpassword, $email){

            // firstly get the hashed password from db
            $hashed_password = $this->getUsersPasswordByEmail($email);
            if(password_verify($currentpassword, $hashed_password)){

                $hash_password = password_hash($newpassword, PASSWORD_DEFAULT);
                $stmt = $this->con->prepare("UPDATE users SET password = ? where email = ?");
                $stmt->bind_param("ss", $hash_password, $email); 

                if($stmt->execute())
                    return PASSWORD_CHANGED;
                return PASSWORD_NOT_CHANGED;   
            }
            else{
                return PASSWORD_DO_NOT_MATCH;
            }
        }

        private function isEmailExist($email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }
    }