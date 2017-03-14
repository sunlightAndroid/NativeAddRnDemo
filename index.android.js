/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    NativeModules,
    View
} from 'react-native';

import PaintView from './NativePaintView';
export default class RNComponent extends Component {

    // 构造
    constructor(props) {
        super(props);
        // 初始状态
        this.state = {
            title: '',
        };
    }

    render() {
        tryCall = () => {
            var rnToastAndroid = NativeModules.MyNativeModule;
            rnToastAndroid.tryCallBack("luo", "131", (errorCallback) => {
                    alert(errorCallback)
                },
                (successCallback) => {
                    alert(successCallback);
                });
        };
        androidback = () => {

            var ANdroidNative = NativeModules.MyNativeModule;
            ANdroidNative.renderAndroidData((Callback) => {
                alert(Callback);

            });
        };

        return (

            <View style={styles.container}>

                <PaintView style={styles.paintview}
                           color={"#FF5984"}
                           raduis={100}
                />
                <Text style={styles.welcome}
                      onPress={this.call_button.bind(this)}
                >
                    React Native 调用原生方法!
                </Text>
                <Text style={styles.instructions}
                      onPress={() => androidback()}
                >
                    获得android回调的数据
                </Text>
                <Text style={styles.instructions}>

                    {NativeModules.MyNativeModule.rnCallNative(this.state.title)}
                </Text>

                <Text style={styles.instructions} onPress={() => tryCall()}>
                    trycallAndroid
                </Text>
            </View>
        );
    }

    call_button() {

        NativeModules.MyNativeModule.rnCallNative('调用原生方法的Demo');
    }

}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#fff9c0',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
    paintview: {
        width: 100,
        height: 100,
        backgroundColor: '#000'
    },
});

AppRegistry.registerComponent('RNComponent', () => RNComponent);
