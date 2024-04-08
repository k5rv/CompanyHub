# Table of Contents
1. [Username Textbox test coverage](#1-username-textbox-test-coverage)
    - [1.a Username Textbox Test Cases for "monsterUser" Bug Verification](#1a-username-textbox-test-cases-for-monsteruser-bug-verification)
    - [1.b Username Textbox Test Cases for Regression](#1b-username-textbox-test-cases-for-regression)
2. [Phone bug report](#2-phone-bug-report)
3. [Electronic window shutter checklist](#3-electronic-window-shutter-checklist)

# 1. Username Textbox test coverage
## 1.a Username Textbox Test Cases for "monsterUser" Bug Verification

### Test Case 1: Latin Alphabet Lower and Upper Case
- Input: "monsterUser"
- Expected Result: Username accepted

### Test Case 2: Latin Alphabet Lower Case
- Input: "monsteruser"
- Expected Result: Username accepted

### Test Case 3: Latin Alphabet Upper Case
- Input: "MONSTERUSER"
- Expected Result: Username accepted

### Test Case 4: Greek Alphabet Lower and Upper Case
- Input: "Μονστερχρήστης"
- Expected Result: Username accepted

### Test Case 5: Greek Alphabet Lower Case
- Input: "μονστερχρήστης"
- Expected Result: Username accepted

### Test Case 6: Greek Alphabet Upper Case
- Input: "ΜΟΝΣΤΕΡΧΡΗΣΤΗΣ"
- Expected Result: Username accepted

### Test Case 7: Cyrillic Alphabet Lower and Upper Case
- Input: "монстрпользователЬ"
- Expected Result: Username accepted

### Test Case 8: Cyrillic Alphabet Lower Case
- Input: "монстрпользователь"
- Expected Result: Username accepted

### Test Case 9: Cyrillic Alphabet Upper Case
- Input: "МОНСТРПОЛЬЗОВАТЕЛЬ"
- Expected Result: Username accepted

### Test Case 10: Latin Alphabet with Greek ο (U+03BF) and Cyrillic е (U+0435)
- Input: "monsterUser"
- Expected Result: Username accepted

### Test Case 11: Latin Alphabet with Cyrillic o (U+043E)
- Input: "monsterUser"
- Expected Result: Username accepted

### Test Case 12: Letters from Latin, Greek and Cyrillic Alphabets that look alike but have different unicode values
Latin: x (U+0078), p (U+0070), m (U+006D), k (U+006B), a (U+0061), e (U+0065), o (U+006F), A (U+0041), B (U+0042), E (U+0045), K (U+004B), M (U+004D), N (U+004E), O (U+004F), P (U+0050), T (U+0054), X (U+0058)
Cyrillic: о (U+043E), а (U+0430), е (U+0435), к (U+043A), р (U+0440), х (U+0445), А (U+0410), В (U+0412), Е (U+0415), К (U+041A), М (U+041C), Н (U+041D), О (U+041E), Р (U+0420), Т (U+0422), Х (U+0425)
Greek: κ (U+03BA), ο (U+03BF), Α (U+0391), Β (U+0392), Ε (U+0395), Κ (U+039A), Μ (U+039C), Ν (U+039D), Ο (U+039F), Ρ (U+03A1), Τ (U+03A4), Χ (U+03A7)
- Input: "xpmkaeoABEKMNOPTXоАВЕКМНОРТХаекорхοАВЕΚΜΝΟΡΤΧκο"
- Expected Result: Username accepted

### Test Case 13: Letters from Armenian and Coptic Alphabets that are the same as Latin
Armenian: O (U+0555), Coptic O (U+2C9E), o (U+2C9F)
- Input: "moOonsterUser"
- Expected Result: Invalid username

## 1.b Username Textbox Test Cases for Regression

### Test Case 1: Latin Alphabet Lower and Upper Case
- Input: "monsterUser"
- Expected Result: Username accepted

### Test Case 2: Latin Alphabet Lower Case
- Input: "monsteruser"
- Expected Result: Username accepted

### Test Case 3: Latin Alphabet Upper Case
- Input: "MONSTERUSER"
- Expected Result: Username accepted

### Test Case 4: Greek Alphabet Lower and Upper Case
- Input: "Μονστερχρήστης"
- Expected Result: Username accepted

### Test Case 5: Greek Alphabet Lower Case
- Input: "μονστερχρήστης"
- Expected Result: Username accepted

### Test Case 6: Greek Alphabet Upper Case
- Input: "ΜΟΝΣΤΕΡΧΡΗΣΤΗΣ"
- Expected Result: Username accepted

### Test Case 7: Greek Alphabet Lower and Upper Case
- Input: "монстрпользователЬ"
- Expected Result: Username accepted

### Test Case 8: Greek Alphabet Lower Case
- Input: "монстрпользователь"
- Expected Result: Username accepted

### Test Case 9: Greek Alphabet Upper Case
- Input: "МОНСТРПОЛЬЗОВАТЕЛЬ"
- Expected Result: Username accepted

### Test Case 10: Latin, Greek, Cyrillic Alphabet Lower Case
- Input: "monsteruserμονστερχρήστηςмонстрпользователь"
- Expected Result: Username accepted

### Test Case 11: Latin, Greek, Cyrillic Alphabet Upper Case
- Input: "MONSTERUSERΜΟΝΣΤΕΡΧΡΉΣΤΗΣМОНСТРПОЛЬЗОВАТЕЛЬ"
- Expected Result: Username accepted

### Test Case 12: Latin, Greek, Cyrillic Alphabet Lower and Upper Case
- Input: "MonsTeRusErΜόνστερχρΗστΗςмОнстРпОльзоВатель"
- Expected Result: Username accepted

### Test Case 13: Username Length Check (assuming max is 50)
- Input: "uSErMonSteRmoRmoNstErMOnstERusErMONsteruSErMONstER"
- Expected Result: Username accepted

### Test Case 14: Minimum Username Length Check (assuming min is 5)
- Input: "uSrMn"
- Expected Result: Username accepted

### Test Case 17: Letters from Latin, Greek, Cyrillic Alphabets that look alike but have different unicode values
- Input: "xpmkaeoABEKMNOPTXоАВЕКМНОРТХаекорхοАВЕΚΜΝΟΡΤΧκο"
- Expected Result: Username accepted

### Test Case 18: Armenian and Coptic Alphabets that are the same as Latin
- Input: "moOonsterUser"
- Expected Result: Invalid username

### Test Case 19: Username with Numbers
- Input: "username1234"
- Expected Result: Invalid username

### Test Case 20: Username with Special Characters
- Input: "U$T!&<c$$|';n{-}XsS><%ttaCk[]_!e;/,()*+-."
- Expected Result: Invalid username

### Test Case 21: Username with Script Text
- Input: \<script\>alert('Hello!');\<\/script\>
- Expected Result: Invalid username

### Test Case 22: Empty Username
- Input: ""
- Expected Result: Invalid username

### Test Case 23: Username with New Line Character
- Input: "username\n"
- Expected Result: Invalid username

### Test Case 24: Username with Emoji
- Input: "username😎"
- Expected Result: Invalid username

### Test Case 25: Username with Chinese Characters
- Input: "username用户名"
- Expected Result: Invalid username

### Test Case 26: Username with Hindi Characters
- Input: "usernameउपयोगकर्तानाम"
- Expected Result: Invalid username

### Test Case 27: Username with Arabic Characters
- Input: "usernameاسمالمستخدم"
- Expected Result: Invalid username

### Test Case 28: Username with Urdu Characters
- Input: "usernameصارفنام"
- Expected Result: Invalid username

# 2. Phone bug report

**Title:** Phone restarts when user turns speaker "on" after dialing a number.

**Priority:** Critical

**Severity:** High

### Description

User dials a number, and when the number is dialed successfully, the user turns on the speaker using the device's speaker button "on", resulting in the phone restarting. This issue reproduces on different phone numbers and networks (Wi-Fi, Ethernet). The problem was found on the most popular device model with the latest software version, and it is not reproduced on the previous software versions (6.2.X.Y) and models. It appears that the latest release can cause this issue.

### Preconditions
1. Model: Polycom VVX 101
2. Software: 6.3.0.14929 Combined

### Steps to reproduce

1. Power on the phone device.
2. Wait for the phone to connect to the network.
3. Dial a number.
4. Wait for the connection to be established.
5. Press the phone device's speaker button to turn it "on".

**Expected result:** The device's speaker should be working, allowing the user to hear audio from the conference utilizing the speaker to participate in the conference.

**Actual result:** The phone device restarts.

### Device information

- **Device Model:** Polycom VVX 101
- **Device Serial Number:** 210235383610C3000006
- **Software Version:** 6.3.0.14929 Combined

# 3. Electronic window shutter checklist
- Check that "Up" opens the shutter completely.
- Check that "Down" closes the shutter completely.
- Check that "Stop" stops the shutter during opening.
- Check that "Stop" stops the shutter during closing.
- Check that pressing "Up" during closing doesn't interfere with the current operation and closes the shutter completely.
- Check that pressing "Down" during opening doesn't interfere with the current operation and opens the shutter completely.
- Check that pressing "Up" more than once during closing stops the shutter and starts to open it.
- Check that pressing "Down" more than once during opening stops the shutter and starts to close it.
- Check that pressing "Up" during opening doesn't interfere with the current operation and opens completely.
- Check that pressing "Down" during closing doesn't interfere with the current operation and closes completely.
- Check that the shutter can receive commands from the remote at the maximum distance stated in the technical documentation.
- Check that the shutter can receive commands from the remote at the maximum and minimum angles stated in the technical documentation.
- Check that the shutter doesn't work with a different remote. (Probably some testing device should emit a set of signals that are designed for different remotes.)
- Check that the remote can operate with two shutters separately if directed correctly.
- Check that if the shutter was turned off during the "Up" operation, after turning it back on, it returns to the initial state (Down).
- Check that if the shutter was turned off during the "Down" operation, after turning it back on, it returns to the initial state (Down).
- Check that if the shutter was turned off during the "Stop" operation, after turning it back on, it returns to the initial state (Down).
- Check that the shutter has no visible damage in areas where elements interact most frequently.
- Check that the remote has no visible damage.
- Check that the remote can be disassembled and work with a different pair of batteries.
- Check that all required documentation is in the package.