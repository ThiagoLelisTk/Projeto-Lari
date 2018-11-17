
package model;

public class Cripitografia {
    public String criptografar(String s){
        String novo = "";
	char[] c = s.toCharArray();
	for(char ch: c){
            int novoch;
            if(ch == '7'){
                novoch = 48;
            } else if (ch == '8'){
                novoch = 49;
            } else if (ch == '9'){
		novoch = 50;
            } else if (ch == 'x'){ 
		novoch = 97;
            }else if (ch == 'y'){
		novoch = 98;
            } else if (ch == 'z'){
		novoch = 99;
            }else{
		novoch = (int)ch + 3;
            }
            novo += (char)novoch;
        }
        return novo;
    }
    
    public String descriptografar(String s){
        String novo = "";
	char[] c = s.toCharArray();
	for(char ch: c){
            int novoch;
            if(ch == '0'){
                novoch = 55;
            } else if (ch == '1'){
		novoch = 56;
            } else if (ch == '2'){
		novoch = 57;
            } else if (ch == 'a'){ 
		novoch = 120;
            }else if (ch == 'b'){ 
		novoch = 121;
            }else if (ch == 'c'){ 
		novoch = 122;
            }else{
		novoch = (int)ch - 3;
            }
            novo += (char)novoch;
        }
        return novo;
    }
}
