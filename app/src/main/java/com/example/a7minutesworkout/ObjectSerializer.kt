package com.example.a7minutesworkout

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


//Class to Cast Object to Bytes and vice Verse based on function calls
class ObjectParser {
    companion object{

        //Cast Object to Bytes
        fun serialize(obj:Any?):ByteArray{
            if(obj==null){
                return ByteArray(0)
            }
            else{
                var baos=ByteArrayOutputStream()
                var oos=ObjectOutputStream(baos)
                oos.writeObject(obj)
                oos.close()
                return baos.toByteArray()
            }
        }
        //cast Bytes to Object
        fun deserialize(byte:ByteArray):Any?{


    var bais=ByteArrayInputStream(byte)
var ois=ObjectInputStream(bais)


    return ois.readObject()



        }


    /*   private fun encodeBytes(bytes:ByteArray):String{
            var buffer= StringBuffer()

            for(byte in bytes){
                buffer.append(((byte.toInt() shr 4) and 0xF + 'a'.toInt()).toChar())
buffer.append(((byte.toInt())and 0xF + 'a'.toInt()).toChar())

            }
return buffer.toString()
        }

        private fun decodeBytes(str:String):ByteArray{
          var bytes=ByteArray(str.length/2)


            for(i in 0..(str.length-1)){
                var c=str[i].toChar()
                bytes.set(i/2,((c.minus('a')).toInt() shl 4).toByte())

                c=str[i+1].toChar()
                bytes.set(i/2,(bytes.get(1/2)+(c.minus('a'))).toByte())

            }


         return bytes
        }
        */





    }





}