import type {Player} from "./Player";
import type {Board} from "./Board";

export interface Game {
    players: Player[]
    board: Board
}
