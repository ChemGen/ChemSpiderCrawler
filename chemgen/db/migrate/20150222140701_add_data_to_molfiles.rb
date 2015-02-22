class AddDataToMolfiles < ActiveRecord::Migration
  def change
    add_column :molfiles, :data, :text
  end
end
