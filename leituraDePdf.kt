import java.io.File
import java.io.InputStream
import java.io.FileWriter
import java.io.BufferedWriter
import kotlin.io.readLine
import kotlin.io.useLines
import kotlin.sequences.forEach

                             
fun main()
{
    var vle = ArrayList<String>()
    var txtFormat = ArrayList<String>()
    var create: File = File("teste.out")
    var media: Float = 0f;
    var mTxt = File("samples/measurements.txt");
    val dic = hashMapOf<String,ArrayList<String>>();

    mTxt.bufferedReader().useLines{ 
        l -> l.forEach{            
            var vl: Int = it.indexOf(';');
            var txt = it.substring(0, vl);

            if(!dic.containsKey(txt)) dic.put(txt,ArrayList<String>());
            dic.get(txt)?.add(it.substring(vl+1));
        }
    };

    dic.forEach{ l ->
        vle.clear();
        l.value.forEach{
            media = (media + it.toFloat())
            media = media / it.count();
            vle.add(it);
        }

        txtFormat.add("${l.key} = ${max(vle,true)} / ${media.toString()} / ${max(vle,false)}");
    }

    txtFormat.sort();
    var txt:String = txtFormat.toString()
    txt = txt.replace("]", "}")
    txt = txt.replace("[", "{")
    
    create.writeText(txt);
    create.createNewFile();
}

fun max(valor: ArrayList<String>, bl: Boolean):String
{
    var min:String = valor[0];
    var max:String = valor[valor.count()-1]
    valor.forEach{
        if(it.toFloat() < min.toFloat()) min = it;
        else if(it.toFloat() > max.toFloat()) max = it;
    }
    if(bl == true) return min
    return max;
}