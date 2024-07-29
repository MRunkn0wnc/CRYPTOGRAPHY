#include <stdio.h>
void main()
{
    char input[]="helloworld";
    int i,j,len=sizeof(input)-1,index;
    char t[]="qwertyuioplkjhgfdsazxcvbnm";
    char enc[len],dec[len];
    for(i=0;i<len;i++)
    {
        int index=input[i]-'a';
        enc[i]=t[index];
    }
    printf("Original string :%s\n",input);
    printf("Encrypted string =%s\t\n",enc);
    for(i=0;i<len;i++)
    {
    for(j=0;j<26;j++)
    {
        if(enc[i]==t[j])
        dec[i]=j+'a';
    }
    }
    printf("Decrypted string=%s\t\n",dec);
}
