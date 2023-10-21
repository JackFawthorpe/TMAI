<script lang="ts">
    import SocketConnectionDisplay from "../SocketConnectionDisplay.svelte";
    import PlayerBrowser from "../PlayerBrowser.svelte";

    export let connected: boolean;
</script>

<div class="w-[40%] bg-white flex flex-col justify-between">
    <PlayerBrowser/>
    <div>
        <SocketConnectionDisplay connected={connected}/>
    </div>
</div>