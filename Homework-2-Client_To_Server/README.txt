Client To Server Program Using UDP
    *NOTE: Any Quotations you see should be interpret as literal input into Command Line

Prerequisites:
    1. Java and Javac is installed on your machine
    2. Network connection is needed to connect outside of your lan
        - Or else, you can only connect to "localhost" or "127.0.0.1"

Compiling the Program:
    How to COMPILE (Window):
        1. Extract the "Client_To_Server.zip(or .rar)" into a Folder of your choice.
        2. Launch Window CMD
        3. "cd" into the Folder.
        4. To Compile the files do the following:
            "javac -d \class\ src\*.java"
    How to COMPILE (Linux/Mac):
        1. Extract the "Client_To_Server.zip(or .rar)" into a Folder of your choice.
        2. Launch Bash Terminal
        3. "cd" into the Folder.
        4. To Compile the files do the following:
            "javac -d \class\ src\*.java"

Running the Program:
    0. If CMD/Terminal is closed, reopen it.
    1. "cd" into the Folder of "Client_To_Server".
    2. "cd" into the "class" folder.
    3. Run the Server Program:
        "java Server" (W/out ".class")
    4. Open another CMD/Terminal for Client Program
    5. "cd" into the same "class" folder.
    6. Run the Client Program:
        "java Client" (W/out ".class")
    7. Follow the Client Program Prompt
