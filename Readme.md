[![Build Status](https://travis-ci.org/ans7991/DM.svg?branch=master)](https://travis-ci.org/ans7991/DM)

A simple java library to download files from internet with resumability feature. In case download is cancelled, it starts from where it was stopped.

Running App:
    
    make build
    make url=http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip location=~/Downloads
    
Running Tests:

    make test
    or
    ./gradlew clean build