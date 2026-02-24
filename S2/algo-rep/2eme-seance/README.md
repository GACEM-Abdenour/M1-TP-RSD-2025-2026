## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Network Communication (Local Network Setup)

### How to Run Between Two PCs

**Step 1: Find the Server PC's IP Address**
- On the PC that will run Server.java: Open Command Prompt and run `ipconfig`
- Look for "IPv4 Address" (e.g., 192.168.1.100)

**Step 2: Update Client Files**
- Edit Client1.java and Client2.java
- Replace `"192.168.x.x"` with the actual IP address from Step 1

**Step 3: Run in Order**
1. On Server PC: Compile and run `Server.java`
2. On Client PC: Compile and run `Client1.java` and/or `Client2.java`

### Troubleshooting
- **Connection refused**: Check the IP address is correct
- **Firewall**: Allow Java through Windows Firewall
- **Port 2004**: Make sure nothing else is using this port

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
