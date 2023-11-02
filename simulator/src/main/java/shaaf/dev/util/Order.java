package shaaf.dev.util;

public class Order {
    public String orderId = null;
    //public String total = null;
    public CreditCard creditCard = null;
    public String billingAddress = null;
    public String name = null;


    public Order() {
    }

    public static class Builder {

        private final Order order;

        public Builder(){
            this.order = new Order();
            order.creditCard = new CreditCard();
        }

        public Builder orderId(String orderId){
            order.orderId = orderId;
            return this;
        }

        public Builder name(String name){
            order.name = name;
            return this;
        }

/*        public Builder total(String total){
            order.total = total;
            return this;
        }
*/

        public Builder billingAddress(String billingAddress){
            order.billingAddress = billingAddress;
            return this;
        }


        public Builder number(String number){
            order.creditCard.setNumber(number);
            return this;
        }

        public Builder expiration(String expiration){
            order.creditCard.setExpiration(expiration);
            return this;
        }

        public Builder nameOnCard(String nameOnCard){
            order.creditCard.setNameOnCard(nameOnCard);
            return this;
        }

        public Order build(){
            return order;
        }

    }

}
