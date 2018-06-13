import React, {Component} from 'react'
import {Table} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import {formatTime} from '@common/util'

export default class TestResults extends Component {
  constructor(props) {
    super(props)
    document.title = '测试结果'

    this.state = {
      list: [],
      loading: false
    }
  }

  componentWillMount() {
    this.setState({
      loading: true
    })
    fetch({
      url: '/api/Ajax/AjaxGetAlltestResult',
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        loading: false,
        list: result.data || []
      })
    })
  }

  render() {
    const columns = [
      {
        title: '序号',
        dataIndex: 'xuhao',
        key: 'xuhao',
        render: (text, record, index) => {
          return <span>{index + 1}</span>
        }
      },
      {
        title: '课本',
        dataIndex: 'bookName',
        key: 'bookName'
      },
      {
        title: '单元',
        dataIndex: 'unitNbr',
        key: 'unitNbr',
        render: (text) => {
          if (!text || text == 0) {
            return '-'
          }
          return text
        }
      },
      {
        title: '分数',
        dataIndex: 'resultScore',
        key: 'resultScore',
        render: (text) => {
          if (text == 100) {
            return <span style={{ color: 'red' }}>100</span>
          }
          return text
        }
      },
      {
        title: '测试类型',
        dataIndex: 'testType',
        key: 'testType',
        render: (text, record) => {
          if (text == 1) {
            return '单元测试'
          }
          if (text == 5) {
            return '自主测试'
          }
        }
      },
      {
        title: '测试用时',
        dataIndex: 'realDuration',
        key: 'realDuration',
        render: (text, record) => {
          return formatTime(text)
        }
      },
      {
        title: '测试日期',
        dataIndex: 'createtime',
        key: 'createtime'
      },
      {
        title: '是否通过',
        dataIndex: 'tongguo',
        key: 'tongguo',
        render: (text, record) => {
          let color = 'rgb(0, 128, 0)'
          let pass = '是'
          if (record.resultScore < 100 && record.resultScore >= 90) {
            color = 'rgb(255, 165, 0)'
          } else if (record.resultScore < 90) {
            color = 'rgb(255, 0, 0)'
            pass = '否'
          }
          return <span style={{ color: color }}>{pass}</span>
        }
      }
    ]
    return (
      <div className='page_test_results'>
        <Panel title='所有测试成绩&测试记录'>
          <Table
            rowKey='createtime'
            size='middle'
            pagination={false}
            loading={this.state.loading}
            dataSource={this.state.list}
            columns={columns}
            >
          </Table>
        </Panel>
      </div>
    )
  }
}