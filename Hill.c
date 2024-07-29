#include <stdio.h>
#include <string.h>

void main() {
    int i, j;
    int key[2][2] = {{2, 3}, {3, 6}};
    char a[] = "attack";
    int len = strlen(a);
    char e[len], num[len], d[len];

    // Convert characters to numbers
    for (i = 0; i < len; i++) {
        num[i] = a[i] - 'a';
    }

    // Encrypt the message
    for (i = 0; i < len; i += 2) {
        e[i] = ((num[i] * key[0][0]) % 26 + (num[i + 1] * key[0][1]) % 26) % 26;
        e[i + 1] = ((num[i] * key[1][0]) % 26 + (num[i + 1] * key[1][1]) % 26) % 26;
    }

    // Convert numbers back to characters
    char enc[len + 1];
    for (i = 0; i < len; i++) {
        enc[i] = e[i] + 'a';
    }
    enc[len] = '\0'; // Null-terminate the string

    printf("Original string: %s\n", a);
    printf("Encrypted string: %s\n", enc);

    // Calculate determinant and its modular inverse
    int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
    if (det < 0) det += 26;
    int det_inv;
    for (i = 0; i < 26; i++) {
        if ((det * i) % 26 == 1) {
            det_inv = i;
            break;
        }
    }

    // Calculate adjugate matrix and inverse key matrix
    int adj[2][2] = {{key[1][1], -key[0][1]}, {-key[1][0], key[0][0]}};
    int inv[2][2];
    for (i = 0; i < 2; i++) {
        for (j = 0; j < 2; j++) {
            inv[i][j] = (adj[i][j] * det_inv) % 26;
            if (inv[i][j] < 0) inv[i][j] += 26;
        }
    }

    // Decrypt the message
    for (i = 0; i < len; i += 2) {
        d[i] = ((e[i] * inv[0][0]) % 26 + (e[i + 1] * inv[0][1]) % 26) % 26;
        d[i + 1] = ((e[i] * inv[1][0]) % 26 + (e[i + 1] * inv[1][1]) % 26) % 26;
    }

    // Convert numbers back to characters
    char dec[len + 1];
    for (i = 0; i < len; i++) {
        dec[i] = d[i] + 'a';
    }
    dec[len] = '\0'; // Null-terminate the string

    printf("Decrypted string: %s\n", dec);
}
