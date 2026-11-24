 **Felsökning**

***Problem med ViewModel i Compose***

När jag arbetade med temaväxlingen upptäckte jag att appen inte fungerade som förväntat. Trots att jag använde remember och mutableStateOf fick jag inte lösningen att fungera.

Med hjälp av ChatGPT undersökte jag problemet och upptäckte att det saknades ett bibliotek för ViewModel i Compose. Jag lade därför till lifecycle-viewmodel-compose i libs.versions.toml och kopplade in det i build.gradle.kts.

Jag behövde även flytta deklarationen av companyViewModel så att den låg före LIANavigatorTheme i MainActivity. Efter ändringarna fungerade lösningen som förväntat.

***Fel ordning på deklarationer***

Vid ett annat tillfälle fick jag ett felmeddelande eftersom en val användes innan den hade deklarerats. När jag undersökte koden upptäckte jag att deklarationen låg på fel plats. Jag flyttade den så att den kom före anropet, och då försvann felet. Här behövde jag ingen hjälp från AI.

 **Hjälpmedel och reflektion**

Under arbetet med projektet har jag använt flera olika hjälpmedel.

ChatGPT har varit det hjälpmedel jag använt mest. Jag har bollat många frågor kring Kotlin, Jetpack Compose och felsökning. Det har hjälpt mig att förstå hur olika delar av koden fungerar, hitta lösningar på problem och komma vidare när jag fastnat. Jag har inte bara kopierat lösningar, utan sett till att förstå vad koden gör och varför den behövs när jag har varit osäker.

Studiematerialet har varit ett viktigt stöd, bland annat när jag skapade projektet på GitHub och kopplade det till Android Studio med hjälp av Git i terminalen.

Klasskamrater har också bidragit med värdefull återkoppling. När jag visade mitt projekt för en klasskamrat upptäckte vi att de röda markeringarna i formuläret inte försvann när användaren började rätta till ett felaktigt fält. Jag ändrade därför valideringen så att felmarkeringen försvinner när användaren börjar skriva.