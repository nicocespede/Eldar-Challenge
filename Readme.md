# **Eldar Challenge**

### El ejercicio se realizó implementando lo siguiente:

• **Enumerado** para las 3 marcas de tarjetas disponibles (`Brand`): opté por un enumerado por la facilidad que ofrece para agregar nuevas opciones.
    
• **Clase** que modela una tarjeta de crédito (`CreditCard`).

• **Clase** que modela una operación (`Transaction`).

• **Clase ejecutable** (`Main`): contiene los métodos pedidos en el enunciado con algunos datos hardcodeados (que se pueden modificar libremente) para realizar las pruebas necesarias y recrear distintos escenarios.

## **Métodos implementados en la clase Main:**

• `createCard(brand, number, name, date)`: crea una tarjeta a partir de los parámetros brindados, si los datos ingresados son válidos emitirá un mensaje de éxito, y en caso contrario mostrará la excepción arrojada, ambos a través de la consola.

• `getCreditCardInfo(card)`: emitirá un mensaje con la información de la tarjeta ingresada (necesita una tarjeta previamente creada con éxito).

• `sendTransaction(card, amount)`: intentará realizar una operación con la tarjeta y el importe ingresados. Si el importe es inválido o la tarjeta está expirada, se arrojará una excepción. En caso contrario, la operación será aceptada siempre y cuando la tarjeta ingresada no supere el límite establecido de $999 (considerando las tasas de servicio cobradas).

• `compareCards(card1, card2)`: indica por consola si las tarjetas ingresadas son las mismas o no.

• `calculateServiceFee(brand, amount)`: indica de cuánto dinero es la tasa de servicio cobrada con la marca de tarjeta e importe ingresados.

## **Comentarios extra:**

• Siéntanse libres de comentar los métodos si se desea probarlos individualmente.

• Hay un bug (señalado en el código con un comentario) claramente identificado que consiste en que la función `parse()` del `DateFormat` realiza el parsing incluso con valores de día y mes inválidos. Decidí no indagar en él ya que considero que no viene al caso para lo solicitado, pero creo que con un poco más de investigación sobre el `DateFormat` puede ser solucionado fácilmente.