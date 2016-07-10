# Microblog Continued

## Description

Add functionality to your microblog.

## Requirements

* Add a password field to the login form. If the user doesn't exist, create a new user and store the password in the `User` object. If the user does exists, check the password and, if it's wrong, don't let them log in (you can decide the details for yourself).
* Add multi-user support by storing your users in a `HashMap<String, User>` and putting your `ArrayList<Message>` inside the `User` object.
* In the `/create-user` route, save the username into the session. In the `/` route, get the username out of the session and subsequently get your `User` object.
* Show a logout button when the user is logged in. It should invalidate the session and refresh the page so you can log in again with a new user.
* Add a form in `messages.html` which lets you delete a message by entering its number.
* Add a form in `messages.html` which lets you edit a message by entering its number and the text you want to replace it with.

![microblog2 - screenshot1.png](https://tiy-learn-content.s3.amazonaws.com/e82c0d7e-microblog2%20-%20screenshot1.png)

![microblog2 - screenshot2.png](https://tiy-learn-content.s3.amazonaws.com/81abb641-microblog2%20-%20screenshot2.png)

## Hard Mode
* Make the microblog persist data by saving the messages a postgres database.
    * The 2 tables will be User and Message
    * Make sure to create the database and tables
    * Message table should have a foreign key to User

## Resources
*[Github Repo](https://github.com/tiy-lv-java-2016-06/microblog-continued)
*[Spark Session Documentation](http://sparkjava.com/documentation.html#sessions)
