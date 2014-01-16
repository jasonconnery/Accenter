#Accenter

I've found ways to normalise accented strings, but I didn't find anything to help generate them.

This is a Groovy script to help convert sentences or android strings.xml files to accented english, for example Settings -> Sëttîñgš , which can then be used with the Developer accented english locale (zz-ZZ) to test localisation.


TODOS:

* The replacement char map is one I lazily made up just from long-pressing keys in OSX. need to put in alternates for characters, like d, t,p,etc that didn't have an immediate replacement.
* when using xml mode , Currently only works on `<string>` items, ignores string arrays.

