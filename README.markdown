#Accenter

I've found ways to normalise accented strings, but I didn't find anything to help generate them.

This is a Groovy script (no reason other than I'm experimenting with groovy) to help convert sentences or android strings.xml files to accented english, for example Settings -> Sëttîñgš , which can then be used with androids Developer accented english locale (zz-ZZ) to test localisation.

## Usage

#### Single Strings
	$ groovy Accenter.groovy "Hello, World"

	Hëłłö, Wörłd
	
#### Android String xml files

If you have an android strings file, you can generate a new one you can include in your `values-zz` folder. If we take a sample input:

````
$ cat strings.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <string name="app_name">SampleApp</string>
    <string name="action_settings">Settings</string>
    <string name="hello_world">Hello world!</string>
    <string name="number_of_fish">You have %d fish!</string>
    <string name="hello_name">Hello, %s!</string>
</resources>

````

We can pass the file to the script to create a new one. The output is just printed, so redirect to a file or something if it looks ok

	$ groovy Accenter.groovy ../../../../examples/strings.xml
	
	<?xml version="1.0" encoding="utf-8"?>
	<resources>
  		<string name="app_name">SámpłëÄpp</string>
  		<string name="action_settings">Sëttîñgš</string>
  		<string name="hello_world">Hëłłö wörłd!</string>
  		<string name="number_of_fish">Yöü hávë %d fîšh!</string>
  		<string name="hello_name">Hëłłö, %s!</string>
	</resources>
	
Notice that formatting strings aren't affected to %s doesn't become %š

TODOS:

* The replacement char map is one I lazily made up just from long-pressing keys in OSX. need to put in alternates for characters, like d, t,p,etc that didn't have an immediate replacement.
* when using xml mode , Currently only works on `<string>` items, ignores string arrays.

