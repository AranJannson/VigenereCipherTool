# Vigenere Cipher Tool

This Java project demonstrates how to crack a Vigenère cipher under specific conditions: the key is reused for encrypting multiple plaintexts. Given two ciphertexts `c1` and `c2`, the program determines the original plaintexts `w1` and `w2`, as well as the encryption key `k`.

---

## Features
- Decrypts ciphertexts encrypted using the Vigenere cipher.
- Determines the original plaintexts and the shared key.
- Employs analysis of key reuse to solve the problem efficiently.

---

## Requirements
- **Java 8** or higher.
- A text file containing a list of 10-letter words, e.g., `10letterwords.txt`.

---

## Input Format
The program expects:
1. **Two ciphertexts**: These should be strings of equal length (encrypted using the same key).
2. **Word List File**: A plain text file containing all possible plaintexts (10-letter words).

---

## Output
The program outputs:
1. The two original plaintexts (`w1`, `w2`).
2. The shared key `k` used for encryption.

---

## Explanation of the Methodology
1. **Key Reuse Analysis**:
   - When the same key is used to encrypt two plaintexts, the XOR of the two ciphertexts (`c1 XOR c2`) results in the XOR of the plaintexts (`w1 XOR w2`).

2. **Iterative Guessing**:
   - Using the list of potential plaintexts (`10letterwords.txt`), the program iteratively guesses possible pairs of plaintexts that satisfy the XOR condition.

3. **Key Recovery**:
   - Once the plaintexts are determined, the key can be reconstructed by comparing each plaintext character to its corresponding ciphertext character.

4. **Validation**:
   - The program validates the solution by re-encrypting the plaintexts with the recovered key and matching them to the provided ciphertexts.

---


## Example
### Input
```plaintext
Ciphertext1: IHRXMTLNTS
Ciphertext2: TRKEYTNWLD
Word List: 10letterwords.txt
```

### Output

```plaintext
Plaintext1: RETICULUMS
Plaintext2: COMPOUNDED
Key: RDYPKZATHA
```

