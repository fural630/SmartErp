<div class="paging clearfix">
	<div class="massaction">
		<table class="tb_common">
			<tr>
				<td style="width:40%" class="td_left">
					<a href="javascript:void(0)" onclick="pageSelectAll();">全选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="pageNoSelectAll();">全不选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="pageUnselected();">反选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					已选择&nbsp;<span id="pageCheckCount">0</span>&nbsp;条
				</td>
				<td class="td_right">操作&nbsp;&nbsp;
					<select class="sel">
							<option id="batchDelete">批量删除</option>
							<option id="batchUpdateStatus">批量修改状态</option>
					</select>
					&nbsp; <a class="btn" onclick="batchOptionSubmit()">提交</a>
				</td>
			</tr>
		</table>
	</div>
</div>
