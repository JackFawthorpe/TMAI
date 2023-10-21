import {Client} from "@stomp/stompjs";

let stompClient;
export const SocketAPI = {
    onConnect: () => {
    },
    onDisconnect: () => {
    },
    connect: () => {
        stompClient = new Client({
            brokerURL: window.location.origin.replace("http", "ws") + "/socket/game/socket",
            onConnect: () => {
                stompClient.subscribe('/topic/game', SocketAPI.onMessage, {});
                SocketAPI.onConnect();
            },
            debug: function (str) {
                console.log(str);
            },
            onStompError: () => {
                console.log("Web Socket Error Occured");
                SocketAPI.onDisconnect();
            },
            onWebSocketError: () => {
                console.log("Web Socket Error Occured");
                SocketAPI.onDisconnect();
            },
            onDisconnect: () => {
                SocketAPI.onDisconnect();
            }
        })
        stompClient.activate();
    },
    disconnect: () => {
        stompClient.deactivate();
    },
    onMessage: (frame) => {
        console.log(frame)
    }
}