<script lang="ts">

    const imageArray = [
        "src/Resources/EmptyHex.svg"
    ];

    // Board Constants px
    const CELL_SIZE = 80
    const BOARD_HORZ_SHIFT = 15
    const MAIN_ROW_COUNT = 9
    const BOARD_VERT_SHIFT = 20
    const BOARD_X_SIZE = 750
    const BOARD_Y_SIZE = 600
    const VERT_ROW_SHIFT = 60
    export let board: Board

    const getTransform = (index) => {
        return `transform:
        translateX(${Math.abs((MAIN_ROW_COUNT - 1)/2 -index) * (CELL_SIZE) / 2 + BOARD_HORZ_SHIFT}px)
        translateY(${index * VERT_ROW_SHIFT + BOARD_VERT_SHIFT}px)`
    }
    const imageStyle = `height: ${CELL_SIZE}px; width: ${CELL_SIZE}px`
    const boardStyle = `height: ${BOARD_Y_SIZE}px; width: ${BOARD_X_SIZE}px`

</script>

<div class="bg-white rounded-xl" style={boardStyle}>
    {#each board.cells as row, i}
        <div class="flex absolute" style={getTransform(i)}>
            {#each row as i}
                <img src={imageArray[0]} style={imageStyle} alt="cell">
            {/each}
        </div>
    {/each}
</div>