# DES-Java

A clean, modular implementation of the **Data Encryption Standard (DES)** algorithm in pure Java, featuring step-by-step trace output in hexadecimal format.

---

## Overview

This repository implements the 64-bit block cipher DES according to the Federal Information Processing Standard (FIPS PUB 46-3). It provides transparent execution tracing with intermediate hexadecimal values across all 16 rounds of the Feistel network.

### Key Features
- **Pure Java Implementation**: No external cryptographic libraries or third-party dependencies required.
- **Full Feistel Network**: Exact 16-round symmetric Feistel cipher structure with 32-bit halves ($L_i, R_i$).
- **Key Schedule Generation**: PC-1 permutation, circular left shift scheduling, and PC-2 permutation producing sixteen 48-bit subkeys ($K_1 \dots K_{16}$).
- **Standard DES Tables**: Specification-compliant tables for Initial Permutation (IP), Final Permutation (FP / $IP^{-1}$), Expansion ($E$), Permutation ($P$), and S-Boxes ($S_1 \dots S_8$).
- **Hexadecimal Trace Formatting**: Clean terminal display of round keys, intermediate round values, mixer stages, and final ciphertext in HEX.
- **Built-in Verification**: Validates calculated ciphertext against standard test vectors.

---

## Project Structure

```text
DES-Java/
├── DES-Java/
│   ├── DES.java            # Main DES encryption coordinator (IP, rounds, swap, FP)
│   ├── DESTables.java      # Standard DES permutation tables, shifts, and S-boxes
│   ├── KeySchedule.java    # 16-round subkey generator (PC-1, shift schedule, PC-2)
│   ├── Mixer.java          # Feistel round mixer: f(R, K) and L ⊕ f(R, K)
│   ├── DESFunction.java    # Feistel function f(R, K): E -> XOR -> S-Box -> P
│   ├── Permutation.java    # Permutation logic, bit shifting, and hex/binary conversions
│   ├── SBox.java           # 8 S-Box substitutions (6-bit input to 4-bit output)
│   ├── TraceConfig.java    # Trace mode configuration (NORMAL / DETAILED)
│   └── Main.java           # Entry point and verification runner
├── .gitignore
└── README.md
```

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK 17 or higher recommended)

### Compile and Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Ankith-Achar-27/des-java.git
   cd des-java
   ```

2. **Compile the source files:**
   ```bash
   javac -d out DES-Java/*.java
   ```

3. **Execute the program:**
   ```bash
   java -cp out Main
   ```

---

## Sample Output

```text
========================================
           DES ENCRYPTION
========================================

Plaintext : 0123456789ABCDEF
Key       : 133457799BBCDFF1

========================================
          DES KEY SCHEDULE
========================================

Original 64-bit key:
133457799BBCDFF1
K1  = 1B02EFFC7072
K2  = 79AED9DBC9E5
...
K16 = CB3D8B0E17F5

========================================
       INITIAL PERMUTATION
========================================

[Split 64-bit block]
L0 = CC00CCFF
R0 = F0AAF0AA

----------------------------------------
ROUND 1
----------------------------------------
L0 = CC00CCFF
R0 = F0AAF0AA
K1 = 1B02EFFC7072
   S-Box output = 5C82B597
   f(R, K)      = 234AA9BB
   L XOR f(R,K) = EF4A6544
L1 = F0AAF0AA
R1 = EF4A6544

...

========================================
             FINAL SWAP
========================================
L16 = 43423234
R16 = 0A4CD995
After Swap = 0A4CD99543423234

[Combine]
Combined block = 0A4CD99543423234
Final Permutation = 85E813540F0AB405

========================================
             CIPHERTEXT
========================================
HEX    : 85E813540F0AB405

========================================
              VERIFICATION
========================================
Expected   : 85E813540F0AB405
Calculated : 85E813540F0AB405
Status     : PASS
========================================
```

---

## Verification Test Vector

| Parameter | Value |
|---|---|
| **Plaintext** | `0123456789ABCDEF` |
| **Key** | `133457799BBCDFF1` |
| **Ciphertext** | `85E813540F0AB405` |
| **Status** | `PASS` |

---

## License

This project is licensed under the MIT License - see the repository for details.
