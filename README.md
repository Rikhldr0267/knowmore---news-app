# knowmore---news-app

So , what we did ?

   1) made recyclerview in activity_main.xml file
   2) made xml for scrolling ( i.e. recyclerview_items.xml )
   3) made a viewholder class that contains items which will be filled by value later and this is to show as a list later on
   4) Made a adapter class

       implement 3 methods

        a) onCreateViewHolder - to inflate and to handle clicks , called while creating views (basically total no of view a screen have = no of times called , then it recycles that views)
        b) getItemCount  -  to count list size
        c) onBindViewHolder   - holder to bind with current item

   5) in Main activity
        a) layoutmanager is created
        b) taking items
        c) putting them in adapter
        d) connecting adapter with recyclerview

   6) Adding volley Library
        -> getting news through this api call
        1)  made a singleton class that encapsulates RequestQueue and other Volley functionality.
        2) in JSONObjectrequest we are getting desired info among all the info's from the url.


        a) add implementation in built.gradle(app level)
        b) add Internet permission in menifest file
