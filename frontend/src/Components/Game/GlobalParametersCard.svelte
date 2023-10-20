<script lang="ts">
    import type {GlobalParameters} from "../../Types/Game";
    import {CellImage} from "../../Types/Board.js";

    export let params: GlobalParameters;

    const waterPercentage = (9 - params.waterToPlace) / 9 * 100;
    const temperaturePercentage = params.oxygenLevel / 14 * 100;
    const oxygenPercentage = (params.temperature + 30) / 38 * 100;
</script>


<div class="card w-[85%] h-fit flex flex-col gap-y-2 py-3 items-center justify-center">
    <div class="globalParamContainer">
        <img class="h-12 w-12" src={CellImage["OCEAN"]} alt="waterDrop">
        <div class="progressBar">
            <div class="activeProgressBar bg-blue-600" style={`width: ${waterPercentage}%`}/>
        </div>
    </div>
    <div class="globalParamContainer">
        <img class="w-12 h-12" src="src/Resources/img/Oxygen.png" alt="oxygen">
        <div class="progressBar">
            <div class="activeProgressBar" style={`width: ${oxygenPercentage}%`}/>
        </div>
    </div>
    <div class="globalParamContainer">
        <img class="h-12 w-12" src="src/Resources/img/Temperature.png" alt="temperature">
        <div class="progressBar">
            <div class="activeProgressBar bg-red-600" style={`width: ${temperaturePercentage}%`}/>
        </div>
    </div>
</div>