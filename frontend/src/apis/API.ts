import { api } from "./configs/axiosConfigs"
import { defineCancelApiObject } from "./configs/axiosUtils"

export const API = {
    getAllCards: async function (cancel = false) {
        const response = await api.request({
            url: `/cards`,
            method: "GET",
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    },
    getGame: async function (cancel = false) {
        const response = await api.request({
            url: `/game`,
            method: "GET",
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    },
    postGame: async function(playerNames: string[], cancel = false) {
        const response = await api.request({
            url: `/game`,
            method: "POST",
            data: playerNames,
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    }
}

const cancelApiObject = defineCancelApiObject(API)