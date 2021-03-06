#include <IRremote.h>

IRsend irsend;

long convertToLong(unsigned char data[4])
{
  unsigned long tempData = 0, returnData = 0;
  
  tempData = data[0];
  tempData <<= 24;
  tempData & 0xFF000000;
  returnData += tempData;

  tempData = data[1];
  tempData <<= 16;
  tempData & 0x00FF0000;
  returnData += tempData;

  tempData = data[2];
  tempData <<= 8;
  tempData & 0x0000FF00;
  returnData += tempData;

  tempData = data[3];
  tempData <<= 0;
  tempData & 0x000000FF;
  returnData += tempData;
  
  return returnData;
   
}


void setup()
{
   Serial.begin(9600);  
   pinMode(8, OUTPUT);

}
void loop()
{
   unsigned char data[4];
  
   if (Serial.available() >= 4)
   {
     for(int i = 0; i < 4; i++)
     {
       data[i] = Serial.read();
     }
     irsend.sendNEC(convertToLong(data), 32);

     //Eat garbage from Serial comm
     while(Serial.available())
       Serial.read();
   } 
}
