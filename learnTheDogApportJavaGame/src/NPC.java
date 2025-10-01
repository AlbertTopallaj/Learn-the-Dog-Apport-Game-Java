

    //TODO:
    // 1. Gör en Constructor för NPC klassen.
    // 2. Ge NPC klassen variablar i typerna "string" för att deklarera e.x: namnet på NPC:n och dess dialog.
    // 3. Gör "get"-metoder som returnera i typen "String" så att vi kan hämta e.x: namnet på NPC:n och dess dialog.
    //

     public class NPC {

     private String name;
     private String message;

          public NPC (String name, String message){
               this.name = name;
               this.message = message;

          }
          
          public String getName(){
               return name;
          }

          public String getMessage(){
               return message;
          }

          public static NPC[] npcs = {
                  new NPC(

                          "Agneta",
                          "Vilken söt hund, du kanske borde fråga gubben i kiosken om en köttbulle att mata hunden med"

                  ),

                  new NPC(

                          "Glenn",
                          "Nämen hallå där, du får mig att minnas tillbaka från när jag hade min hund. " +
                                  "Jag kastade apport hela tiden med den, jag minns att min hund sprang extra bra om det var en pinne som kastades"
                  ),

                  new NPC(

                          "Kurt",
                          "Hej, kul och se dig här vid vattnet. Passa på att ta ett dopp eller sola lite. " +
                                  "Hunden älskar också solen"
                  ),

                  new NPC(

                          "Kent",
                          "Oj vad bra att du är här! Jag har tappat bort mina bilnycklar, " +
                                  "kan inte du skicka din hund på sök?"

                  ),

                  new NPC(

                          "Gunnar",
                          "Hallå då, hunden ser rätt så hungrig ut, ge den lite mat vetja"

                  )

          };

          public static NPC getNPC(String npcInput){

               for (NPC npc: npcs ){
                    if (npc.name.equals(npcInput.toLowerCase())) {
                         return npc;
                    }
               }
               return null;
          }
     }



