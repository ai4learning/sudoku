import React, {Component} from 'react'
import Panel from '@components/Panel'

export default class BaseInfo extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <Panel title='基本信息'>
      </Panel>
    )
  }
}