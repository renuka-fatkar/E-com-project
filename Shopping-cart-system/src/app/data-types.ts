export interface product {
    productId:number;
    name:string;
    price:number;
    category:string;
    specifications:string;
    img:string
}

export interface Cart{
    cartId:number;
    userName:string;
    quantity:number; 
    prodId:number;
    prodName:string;
    price:number;
    specifications:string;
    img:string;
}

export interface OrderDetails{
    orderdetailId:number;
    nameOfUser:string;
    order_date:string;
    addressOfCust:string;
    phone:string;
    productId:string;
    quantity:number;
    amount:number;
    transactionId:string;
}

export interface MyData {
    data: string;
  }
  
export interface Rating {
    rateId:number;
    userId:number;
    prodId:number;
    rating:number;
    comment:string;
}