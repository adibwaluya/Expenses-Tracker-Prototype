<?php

    define('DB_HOST', 'localhost');
    define('DB_USER', 'root');
    define('DB_PASSWORD', '');
    define('DB_NAME', 'my_app');

    define('USER_CREATED', 101);
    define('USER_EXISTS', 102);
    define('USER_FAILURE', 103);

    define('USER_AUTHENTICATED', 201);              // Successfully authenticated
    define('USER_NOT_FOUND', 202);                  // When the user does not exist in db
    define('USER_PASSWORD_DO_NOT_MATCH', 203);      // When the password does not match 
    
    define('PASSWORD_CHANGED', 301);
    define('PASSWORD_DO_NOT_MATCH', 302);
    define('PASSWORD_NOT_CHANGED', 303);
    
    
    
    
    
    
    