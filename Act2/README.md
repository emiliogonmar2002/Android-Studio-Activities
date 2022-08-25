# android-studio-act2
Activity for my Android Development course

Create an app with the following parts:

### Main activity

- When the activity starts loads 2 values to show as a greeting. Use SQLite for this.
- It has 2 buttons to load 2 differente activities (My Hobbies, and My Friends)

**My hobbies**
- Has an empty textview
- Has an empty edittext
- Has a button
- If a user Hobby is saved on the local Sqlite DB it should be shown on creation, if not the empty text view remains empty.
- The user can write a value on the edittext and press the button at any time. This will update the value on the SqliteDB

**My Friends** - The interface will now show the following elements:
- An entry text for name
- An entry text for hobby
- A button to save - When the user presses this button the app saves the name/hobby on the entry texts as a record in a table in a SQLite DB.
- A button to search - When the user presses this button the app retrieves the text from the name and updates the hobby with the one recorded for that friend in the DB.
- A button to delete - When the user presses this button the app erases the record that has the name of the friend currently on the text entry.


https://user-images.githubusercontent.com/72105206/186696344-0add072d-9244-442a-9d44-96cfdfe4b25e.mov

