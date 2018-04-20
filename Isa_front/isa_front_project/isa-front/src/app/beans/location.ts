import {Projection} from "./projection";
export class Location {
  public id: number;
  public name: string;
  public locationType: string;
  public address: string;
  public description: string;
  public projections: Projection[] = [];
}
