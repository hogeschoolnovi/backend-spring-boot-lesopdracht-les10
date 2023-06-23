# Opdracht:
## "Persons"

- Je gaat dit Spring project verder uitbreiden.
- Voeg een __GET__ method (‘/persons’)toe die op basis van een index (int) de zoveelste persoon uit de lijst retourneert (via ArrayList.get()). Als die aanwezig is, retourneer je deze als JSON data. Zoek zelf uit welke HTTP status je moet retourneren (resource gevonden / niet gevonden).
- Voeg een __DELETE__ method (‘/persons’) toe die een persoon uit de lijst verwijdert op basis van een naam (String). Als die aanwezig is, verwijder je deze en retourneert HTTP status 204 (No Content), anders status 404 (NotFound).
- Extra: 
  - Maak een __GET__ method (‘/persons/search’) die een lijst van personen retourneert op basis van een substringparameter.
- Test het in Postman