<script>

    import {SocketAPI} from "../../apis/SocketAPI.ts";

    const handleReconnect = () => {
        SocketAPI.connect();
    }

    const handleDisconnect = () => {
        SocketAPI.disconnect();
    }

    export let connected;
</script>

<div class="border-t py-3 ps-3">
    <div class="flex w-100 justify-between items-center">
        {#if connected}
            <div>
                <div class="dot connected"/>
                <span>Connected</span>
            </div>
            <div class="pe-3">
                <button class="btn btn-primary" on:click={handleDisconnect}>
                    Disconnect
                </button>
            </div>
        {:else}
            <div>
                <div class="dot disconnected"/>
                <span>Disconnected</span>
            </div>
            <div class="pe-3">
                <button class="btn btn-primary" on:click={handleReconnect}>
                    Reconnect
                </button>
            </div>
        {/if}
    </div>
</div>

<style>

    .dot {
        height: 10px;
        width: 10px;
        border-radius: 50%;
        display: inline-block;
        margin-right: 10px;
    }

    .disconnected {
        background-color: red;
    }

    .connected {
        background-color: green;
    }
</style>