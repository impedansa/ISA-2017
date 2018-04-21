import {Location} from "./location";
import {ProjectionTime} from "./projectionTime";
export class Projection{
  public id: number;
  public name: string;
  public projectionType: string;
  public genre: string;
  public director: string;
  public duration: number;
  public image: File;
  public avgRate: number;
  public description: string;
  public price: number;
  public projectionTimes: ProjectionTime[] = [];
}
