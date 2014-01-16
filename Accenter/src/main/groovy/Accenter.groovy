import groovy.xml.MarkupBuilder

class Accenter
{
    private static map = ['a':'á' , b:'b' , c:'č' , d:'d' , e:'ë' , f:'f' , g:'g' , h:'h' , i:'î', j: 'j' , k:'k' , l:'ł' , m :'m' , n:'ñ', o:'ö' , p:'p' , q:'q', r:'r' , s:'š' , t:'t' , u:'ü' , v:'v' , w:'w' , x:'x' , y:'ÿ' , z:'ż' , A:'Ä',B:'B',C:'Č',D:'D',E:'Ę',F:'F',G:'G',H:'H',I:'Î',J:'J',K:'K',L:'Ł'] ;
    
    public static String replace(String input)
    {
        StringBuilder sb = new StringBuilder();
        boolean skipToSpace = false ;

        for( int i = 0  ; i < input.length() ; i++ )
        {
            char c = input.charAt(i);

            if( skipToSpace)
            {
                sb.append(c);

                if( c == ' ')
                {
                    skipToSpace = false ;
                }
                continue;
            }

            if( c == '%' || c == '$')
            {
                skipToSpace = true ;

                sb.append(c);
                continue;
            }
            
            String newC = map[new String(c)];
            
            if(  newC == null )
            {
                sb.append(c);
            }
            else
            {
                sb.append(newC);
            }
        }
        
        sb.toString();
    }

    public static void processXml(String xmlPath)
    {
        File xmlFile = new File(xmlPath);

        if( ! xmlFile.exists() )
        {
            System.err.println("File " +xmlFile +" Does not exist!");
            System.exit(1);
        }

        def xml=new XmlSlurper().parse(xmlFile.absolutePath);

        def writer = new StringWriter()
        def outXml = new MarkupBuilder(writer)

        outXml.doubleQuotes = true ;
        outXml.mkp.xmlDeclaration(version:'1.0',encoding:"utf-8")

        outXml.resources() {
            xml.string.each {

                String convertedText = Accenter.replace(it.text());

                string(name:it.@name.text() , convertedText )
            }
        }


        println writer.toString()

    }

    public static void main(String[] args)
    {
        if( args.length < 1 )
        {
            println Accenter.replace("the quick brown fox jumped over the lazy doge. THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGE");
            return ;
        }

        String firstArg = args[0] ;
        if( firstArg.endsWith(".xml"))
        {
            processXml(firstArg);
            return ;
        }

        for( String s : args)
        {
            println Accenter.replace(s);
        }
    }
}
