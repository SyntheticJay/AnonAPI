## Notice

Unfortunately, AnonFile shut down. This will no longer work.

# AnonAPI
This is AnonAPI, a simple library designed to contact AnonFile to send `post` and `get` requests.
In short, this allows you to simply upload a file, and retrieve the data of it either through a `String` or `JSONObject`
This is maven based, because It's easier to add dependencies, it's also easier for you!

## Usage

To use AnonAPI, simply instantiate it in your class of choice.
e.g.
`public final AnonAPI api = new AnonAPI("tokenhere");`

Right now, the token has no feature, but I plan on adding something to do with it but for now it's just there.

There is `3` functions AnonAPI can do right now:

`upload(File file)` -> `Upload's a file to anonFile, it's String based so it will return the link.`
`String get(String id)` -> `Retrieve's the information of the file in the form of JSON. easy to read as pretty formatting is added.`
`JSONObject get(String id` -> `Retrieve's the information of the file in the form of a JSONObject, making it easy to select different values of the body.`

The ID can be obtained by simply using the function `getID(String link)` and pass in the link of the file. (https://anonfile.com/{id} as given by the upload() function.

## READ BEFORE USING

To be able to use AnonAPI, please be sure to give me a bit of credit! Anywhere is good enough, just show your bro love for making this! Also, if you can, hit me up telling me what you did with this! `bytearrays@protonmail.com`

Tancc you very much
~Jay
