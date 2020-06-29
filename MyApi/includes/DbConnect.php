<?php

    class DbConnect{

        private $con;

        // Connect to database
        function connect(){

            // Get current directory
            include_once dirname(__FILE__)  . '/Constants.php';

            $this->con = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);

            // Check if connection successful or not
            if(mysqli_connect_errno()){
                echo "Failed to connect" . mysqli_connect_error();
                return null;
            }

            return $this->con;
        }
    }