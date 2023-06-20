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
</script>

<div class="h-[{BOARD_Y_SIZE}px] w-[{BOARD_X_SIZE}px] bg-white rounded-xl">
    {#each board.cells as row, i}
        <div class="flex absolute" style={getTransform(i)}>
            {#each row as i}
                <img src={imageArray[0]} alt="cell" class="h-[{CELL_SIZE}px] w-[{CELL_SIZE}px]">
            {/each}
        </div>
    {/each}
</div>