export interface Order {
    id: number;
    price: number;
    dishName: string;
    drinkName: string;
    customerName: string| null;
}

export interface OrderDto {
    price: number;
    ktmScore: number;
    dishIds: number[];
    drinkIds: number[];
    customerId: string;
    date: string;
}