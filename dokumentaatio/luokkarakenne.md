**Luokkarakenne:** Jokaista pelikorttia vastaa Kortti-luokan olio. Kortti voi olla joko pakassa, pöydällä kuvapuoli näkyvissä tai pöydällä kuvapuoli alaspäin. Pakalle on oma luokkansa, ja pöydällä olevat kortit kuuluvat Korttipino-luokan olioille.

Korttipinoon voi kuulua sekä kortteja, joiden kuvapuolet ovat näkyvissä, että kortteja, joiden kuvapuolet ovat alaspäin. Edellisille on oma NakyvaKortti-luokkansa, jonka oliot kuuluvat Korttipino-olioille, kun taas jälkimmäiset kuuluvat Korttipinoille suoraan.

Pasianssi-luokka yhdistää Korttipinot ja Korttipakan yhdeksi kokonaisuudeksi, jota käyttöliittymä voi käyttää. Korttien siirtämislogiikkaa on abstrahoitu Kortinsiirtäjä-luokkaan, poistamislogiikkaa on abstrahoitu Kortinpoistaja-luokkaan ja korttien vertailuun käytettäviä metodeja on Kortinvertailija-luokassa. Lisäksi pasianssin alustamisessa käytettäviä apumetodeja on abstrahoitu Pakantayttaja- ja Pasianssinalustaja-luokkiin.
