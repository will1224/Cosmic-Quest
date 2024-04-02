# 🚀 Cosmic Quest: Stellar Treasures

---

## Description
Cosmic Quest: Stellar Treasures is designed to be an interactive, educational tool that teaches students aged 8-14 about our solar system and basic scientific principles through gameplay. Coded using Java, our game is accessible on computers and does not require an internet connection, ensuring wide accessibility and ease of use. Players assume the role of an astronaut navigating through space, starting from the Sun and moving outward through the solar system. The core mechanics involve answering trivia questions to gather fuel, which enables the player to travel to the next planet. This approach combines learning with goal-oriented gaming, encouraging players to engage with educational content to progress.

---

## Required Libraries & Third-Party Tools
- **Java Development Kit** (JDK) - version 21 
    - Compiler and runtime environment for Java applications
- **JSON.simple** - [version 1.1.1](http://www.java2s.com/Code/Jar/j/Downloadjsonsimple111jar.htm) (for JSON parsing) 
    - Lightweight Java library for parsing JSON data
- **Java Swing** (for GUI)
    - Java library for creating graphical user interfaces
- **JavaDoc** (for documentation)
    - Tool for generating API documentation from Java source code
- **JUnit** (for testing)
    - Java testing framework for unit testing
- **JavaUtil** (Java utility classes) 
    - Standard Java utility classes for common programming tasks
- **AWT** (Abstract Window Toolkit)
    - Java library for creating user interfaces, deprecated in favor of Swing
- **Java.IO** (Input/output classes) 
    - Java package for input/output operations
- **IntelliJ IDEA Community Edition** (Building and running the code)
  - Used to build and run the code locally in the case that there is no working executable file.

Ensure that you have these libraries and tools properly installed and configured to build and run Cosmic Quest: Stellar Treasures.

---

## Building the Software
First-time setup instructions must be followed to build the executable for the first time after installing IntelliJ. If first-time setup has already been completed, then skip to <b>Step 5</b>.

**Step 1:** Install Java Development Kit (JDK)<br>
Download and install the appropriate JDK for your operating system from the official Oracle website or any other trusted source. Follow the installation instructions provided.

**Step 2:** Extract the folder containing the game<br>
Open File Explorer and extract the contents of the game folder.

**Step 3:** Open IntelliJ and open the newly extracted group7 folder<br>
Navigate to the located of the newly extracted group7 folder and open it in IntelliJ.

**Step 4:** Setup the JAR build<br>
1. In IntelliJ, click <i>File > Project Structure</i>.
2. Click <i>Artifacts</i> under Project Settings.
3. Click the Add (+) button.
4. Click <i>JAR</i> > <i>From modules with dependencies</i>.
5. In the menu that pops up, set the main class by clicking on the file explorer icon. Switch to the project tab on the window that appears, and expand the project directory to locate the Main.java file in `/group7/src/Main.java`.

**Step 5:** Build the code
1. Click <i>Build</i> > <i>Build Artifacts</i> in IntelliJ.
2. Click <i>Rebuild</i>.

The executable .jar file will then be generated and is located within the `/group7/out/production/artifacts/group7_jar` folder.

Double-click on the .jar file to run it. Alternatively, you can open a terminal or command prompt, navigate to the game directory using the cd command, and then execute the .jar file using the `java -jar group7.jar` command.

## Run Game Locally Using IntelliJ

**Step 1 and Step 2 are the same as above**

**Step 3:** Open the folder in IntelliJ IDEA
Open IntelliJ and open the group7 folder as a new project.

**Step 4:** Build and run the game
Open a new terminal window in IntelliJ and type in the following commands to build and run the code.<br>Windows:<br>`javac -cp .;json-simple-1.1.1.jar src/Main.java`<br>`java -cp .;json-simple-1.1.1.jar src.Main`<br>Mac/Linux:<br>`javac -cp .:json-simple-1.1.1.jar src/Main.java`<br>`java -cp .:json-simple-1.1.1.jar src.Main`

---

## User Guide 

1. **Login Page**<br>
Upon launching the game, you'll be directed to the login page. If you already have an account, enter your account details (username and password) and click the "Login" button. If you don't have an account yet, click the "Create Account" button to register.
<br>

2. **Account Creation** <br>
If you choose to create an account, you'll be prompted to enter your desired username and password. After entering your credentials, click the "Create Account" button to register. Once your account is created, you'll be automatically logged in and redirected to the level selection screen.
<br>

3. **Level Selection** <br>
After logging in, you'll be taken to the level selection screen. Start at the Sun, the first level/planet, by clicking on it. Each planet represents a level, and you must progress through them sequentially.
<br>
<br>

4. **Educational Lesson** <br>
Before each trivia part, you'll encounter an educational lesson page. Learn interesting facts and principles related to each specific planet. Take your time to read through the lesson content before proceeding to the trivia questions.
<br>
<br>

5. **Trivia Gameplay** <br>
After completing the educational lesson, you'll move on to the trivia gameplay section.
Answer each trivia question correctly to collect points (fuel) needed to advance to the next level/planet.
You must answer a minimum of 5 questions correctly to unlock the next level/planet.
<br>

6. **Bonus Level** <br>
Reach the outermost planet to complete the main game! Upon completion, you may unlock a bonus level featuring additional celestial phenomena like black holes. Explore the bonus level and learn more about these fascinating cosmic objects.
<br>
<br>

7. **Exiting the Game** <br>
You can exit the game at any time by closing the application window. Your progress will be saved automatically, allowing you to log in again later and resume from where you left off.
<br>
<br>

8. **Logging In Again** <br>
When you return to play again, simply launch the game and enter your account details on the login page. You'll be logged in to your existing account, and you can continue your cosmic journey from your last saved progress.

---
## Accessing Documentation

To access the documentation for the project, extract the <b>javadoc.zip</b> file located in /group7/documentation. In the newly extracted folder, open the <b>package-summary.html</b> file located in the following file path: `/group7/documentation/javadoc/src/package-summary.html`.

---

## Teacher Mode

To access Teacher Mode, enter the designated username and password provided below:

Username: *education* <br>
Password: *cosmic123*

Teacher Mode allows educators to customize trivia questions and track student progress.

---

## Developer Mode

To access Developer Mode, enter the designated username and password provided below:

Username: *developer* <br>
Password: *cosmic456*

Developer Mode allows the user to access backend code and commit changes.

---

## Additonal Information

For any issues or inquiries, please contact rluo57@uwo.ca


Enjoy your cosmic journey through the solar system! :)

Best,

Team 7
