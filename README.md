# Getting Started

This is a simple Spring Boot application (AlgoV) that is like a coding challenge platform, the whole purpose of this application is actually to consume an AWS service that randomly generates strings (some are palindrome and some aren't) and expects a reply back from the client with what string are palindrome, this needs to be done in 3 seconds or else the connection will be closed.

To make it more useful i just added a business case around it (code platform simple application)


# Java Code

More algorithms by creating new classes extending from 'Algorithm' and provide template for this (which is the class and method skeleton) 

You would need also a verification class which runs some unit test cases against the submitted raw solution, the palindrome implementation is using AWS service to do the unit testing for it, it is possible to use local files as well to test the submitted code.
