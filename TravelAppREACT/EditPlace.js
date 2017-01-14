
'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TextInput,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View,
  BackAndroid,
    Picker
} from 'react-native';

import Button from 'react-native-button';
const Item = Picker.Item;

class EditPlace extends Component {

  constructor(props) {
    super(props);
    this.state = {
        countryLabel: props.place.country,
        cityLabel: props.place.city,
	    ratingLabel: props.place.rating
    }
  }

  navigate(routeName) {
      this.props.navigator.push({
        name: routeName
      });
    }

    edit() {
        this.props.callback(this.state.countryLabel, this.state.cityLabel, this.state.ratingLabel, this.props.placeIndex);
        this.props.navigator.pop();
    }

  render() {
    return (
      <View style={styles.container}>

        <TextInput style={{width: 70,height: 40, borderColor: 'gray', borderWidth: 1}}
          onChangeText={(text) => this.setState({countryLabel: text})}
          value={this.state.countryLabel}
        />
        <TextInput style={{width: 70,height: 40, borderColor: 'gray', borderWidth: 1}}
          onChangeText={(text) => this.setState({cityLabel: text})}
          value={this.state.cityLabel}
        />

          <Picker
              style={styles.picker}
              selectedValue={this.state.ratingLabel}
              onValueChange={this.onValueChange.bind(this, 'ratingLabel')}>
              <Item label="1" value="1" />
              <Item label="2" value="2" />
              <Item label="3" value="3" />
              <Item label="4" value="4" />
              <Item label="5" value="5" />
              <Item label="6" value="6" />
              <Item label="7" value="7" />
              <Item label="8" value="8" />
              <Item label="9" value="9" />
              <Item label="10" value="10" />
          </Picker>

        <Button onPress={ this.edit.bind(this) }>Save</Button>
        <Button onPress={ this.navigate.bind(this, "chartView") }>Chart</Button>

      </View>
    );
  }

    onValueChange = (key: string, value: string) => {
        const newState = {};
        newState[key] = value;
        this.setState(newState);
    };
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'flex-start',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
        padding: 10,
        paddingTop: 80
    },
    input: {
        height: 50,
        marginTop: 10,
        padding: 4,
        fontSize: 18,
        borderWidth: 1,
        borderColor: '#48bbec'
    },
    button: {
        height: 50,
        backgroundColor: '#48BBEC',
        alignSelf: 'stretch',
        marginTop: 10,
        justifyContent: 'center'
    },
    buttonText: {
        fontSize: 22,
        color: '#FFF',
        alignSelf: 'center'
    },
    heading: {
        fontSize: 30,
    },
    error: {
        color: 'red',
        paddingTop: 10
    },
    success: {
        color: 'green',
        paddingTop: 10
    },
    loader: {
        marginTop: 20
    },
    picker: {
        width: 100,
    }
});

export default EditPlace;