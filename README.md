# eShop
Șablonul Singleton a fost folosit în clasa EShop, care sigură că există doar o instanță a clasei EShop, oferind un punct global de acces la magazinul online.
Șablonul Factory Method, a fost utilizat în interfața PaymentMethodFactory și implementările sale (CardPaymentFactory, CashPaymentFactory, LoanPaymentFactory, CryptoPaymentFactory), ca să definește o interfață pentru crearea metodelor de plată.
Șablonul Strategy, am utilizat în interfața DeliveryStrategy și implementările sale (InStoreDelivery, HomeDelivery, PickUpPointDelivery), ca să definește mai multe algoritmi de livrare, încapsulează fiecare algoritm și îi face interschimbabili. Acest lucru permite clientului (de exemplu, clasa Cart) să aleagă o strategie de livrare în timpul rulării.
Am utilizat Observers la watchList si pentru mesajele să anunță folositorul.
Clasa LoanFacade am creat să acționeze ca o interfață simplificată, un punct de intrare pentru gestionarea plăților de împrumut.
