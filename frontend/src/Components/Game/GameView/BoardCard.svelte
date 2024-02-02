<script lang="ts">

    import type {Board} from "../../../Types/Board";
    import {CellImage} from "../../../Types/Board";
    import type {Game} from "../../../Types/Game";
    import {gameStore, subscribeToStore} from "../../../apis/GameStore";

    let game: Game = null;
    subscribeToStore(gameStore, (value) => (game = value));
    let board: Board;

    $:  board = game?.board;

    // Board Constants px
    const CELL_SIZE = 80
    const BOARD_HORZ_SHIFT = CELL_SIZE / 4
    const BOARD_VERT_SHIFT = CELL_SIZE / 4
    const VERT_ROW_SHIFT = 3 / 4 * CELL_SIZE
    const MAIN_ROW_COUNT = 9
    const BOARD_X_SIZE = MAIN_ROW_COUNT * CELL_SIZE + 2 * BOARD_HORZ_SHIFT;
    const BOARD_Y_SIZE = MAIN_ROW_COUNT * (VERT_ROW_SHIFT + 2) + 2 * BOARD_VERT_SHIFT

    const getTransform = (index) => {
        return `transform:
        translateX(${Math.abs((MAIN_ROW_COUNT - 1) / 2 - index) * (CELL_SIZE) / 2 + BOARD_HORZ_SHIFT}px)
        translateY(${index * VERT_ROW_SHIFT + BOARD_VERT_SHIFT}px)`
    }
    const imageStyle = `height: ${CELL_SIZE}px; width: ${CELL_SIZE}px`
    const boardStyle = `height: ${BOARD_Y_SIZE}px; width: ${BOARD_X_SIZE}px`
</script>

<div class="card" style={boardStyle}>
    {#each board.cells as row, i}
        <div class="flex absolute" style={getTransform(i)}>
            {#each row as i}
                <img src={CellImage[i.type]} style={imageStyle} alt="cell">
            {/each}
        </div>
    {/each}
</div>