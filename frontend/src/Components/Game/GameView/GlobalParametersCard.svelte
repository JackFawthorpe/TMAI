<script lang="ts">
    import type {Game, GlobalParameters} from "../../../Types/Game";
    import {CellImage} from "../../../Types/Board.js";
    import {gameStore, subscribeToStore} from "../../../apis/GameStore";

    let game: Game = null;
    subscribeToStore(gameStore, (value) => (game = value));

    let params: GlobalParameters = game?.globalParameters;

    const waterPercentage = (9 - params.waterToPlace) / 9 * 100;
    const temperaturePercentage = params.oxygenLevel / 14 * 100;
    const oxygenPercentage = (params.temperature + 30) / 38 * 100;
</script>


<div class="card w-[85%] h-fit flex flex-col gap-y-2 py-3 items-center justify-center">
    <div class="globalParamContainer">
        <img alt="waterDrop" class="h-12 w-12" src={CellImage["OCEAN"]}>
        <div class="progressBar">
            <div class="activeProgressBar bg-blue-600" style={`width: ${waterPercentage}%`}/>
        </div>
    </div>
    <div class="globalParamContainer">
        <img alt="oxygen" class="w-12 h-12" src="src/Resources/img/Oxygen.png">
        <div class="progressBar">
            <div class="activeProgressBar" style={`width: ${oxygenPercentage}%`}/>
        </div>
    </div>
    <div class="globalParamContainer">
        <img alt="temperature" class="h-12 w-12" src="src/Resources/img/Temperature.png">
        <div class="progressBar">
            <div class="activeProgressBar bg-red-600" style={`width: ${temperaturePercentage}%`}/>
        </div>
    </div>
</div>