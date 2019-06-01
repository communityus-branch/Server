# RemakeScape
*RemakeScape* is a project which I have started and plan to work on for the next few years. It is a fork using the RSMOD repo.
It's goal is to be a one to one mock up of the current OSRS 179 build. Progress is slow and a server will eventually be up and running once enough content is released.

## Live Server
We currently do not have a live server but will hopefully soon do nightly updates to it.

## Getting Started
So you want to build this project for yourself? Below is a short summary of the steps needed to build this server project

### Step 1:
Clone the repository <br>
``git clone https://github.com/remakescape/server.git ./Server``

### Step 2
Build the gradle project. <br>
``gradle build -x test``

### Step 3
Upload revision cache to `Server/data/cache`<br>
Upload the xteas.json to `Server/data/xteas/xteas.json`

### Step 4
Setup the server project.<br>
``gradle install -x test``

### Step 5
Run the server.<br>
`gradle run`