# Java OOP Chat System

A console-based chat application built in Java demonstrating core Object-Oriented Programming principles.

---

## How to Run

### On OnlineGDB (recommended for quick testing)
1. Go to [onlinegdb.com](https://www.onlinegdb.com) and set language to **Java**
2. Paste the contents of `Main.java` into the editor
3. Click **Run**

### On your local machine
```bash
javac Main.java
java Main
```

> Requires Java 16+ (uses switch expressions and `.toList()`)

---

## Menu Options

| # | Feature | Description |
|---|---------|-------------|
| 1 | Create User | Add a regular user or an Admin |
| 2 | Send Message | Send a text message from one user to another |
| 3 | View Messages | Display all messages in a user's inbox |
| 4 | Delete a Message | Remove a message from a user's inbox |
| 5 | Edit a Message | Modify the content of an existing message |
| 6 | Broadcast | Admin sends a message to **all** users at once |
| 7 | Exit | Quit the program |

---

## Class Design

All classes live in a single file (`Main.java`) for compatibility with online compilers.

```
Main.java
│
├── abstract class Message          # Base class for all messages
│   ├── class TextMessage           # A regular text message
│   └── class BroadcastMessage      # A message sent to all users
│
├── class User                      # Represents a chat user
│   └── class Admin extends User    # Admin user with broadcast ability
│
├── class ChatManager               # Manages users and message operations
│
└── public class Main               # Entry point — interactive menu loop
```

### OOP Concepts Used

| Concept | Where |
|---------|-------|
| **Encapsulation** | All fields are `private`; accessed via getters/methods |
| **Inheritance** | `Admin extends User`, `TextMessage / BroadcastMessage extend Message` |
| **Abstraction** | `Message` is abstract with `getContent()`, `setContent()`, `getType()` |
| **Polymorphism** | `getType()` returns `"TEXT"` or `"BROADCAST"` depending on object type |

---

## Example Flow

```
1 → Create User → "Ali"        (regular)
1 → Create User → "Sara"       (regular)
1 → Create User → "Mo"         (Admin)
2 → Send Message → Ali → Sara → "Hello Sara!"
6 → Broadcast → Mo → "System maintenance at 10pm"
3 → View Messages → Sara
```

**Output:**
```
── Sara's inbox ──
  [0] [10:30] [TEXT] From Ali: Hello Sara!
  [1] [10:31] [BROADCAST] From Mo: [ADMIN] System maintenance at 10pm
```

---

## Extra Features (Small Challenge)

- **Delete a message** — removes a message by index from a user's inbox
- **Edit a message** — modifies message content in-place, prefixed with `[edited]`
- **Broadcast** — Admin-only feature; sends a `BroadcastMessage` to every other user simultaneously

---

## File Structure

```
Main.java     → All source code (single file)
README.md     → This file
```
