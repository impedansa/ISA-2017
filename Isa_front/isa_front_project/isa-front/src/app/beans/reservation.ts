import {User} from "./user";
import {ProjectionTime} from "./projectionTime";
import {Projection} from "./projection";
import {ProjectionRoom} from "./projectionRoom";
export class Reservation{
  public id: number;
  public userCreatedReservation: User;
  public invitedFriends: User[] = [];
  public projection: Projection;
  public projectionTime: ProjectionTime;
  public projectionRoom: ProjectionRoom;
}
