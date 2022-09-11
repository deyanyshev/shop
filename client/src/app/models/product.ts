import {Type} from "./type";
import {Country} from "./country";

export class Product {
  id: number;
  name: string;
  description: string;
  img: string;
  type: Type;
  country: Country;
  cost: number;
}

