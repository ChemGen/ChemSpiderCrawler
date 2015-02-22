class CompoundsController < ApplicationController

  def search
    m = params[:m].to_s.split(',')
    v = params[:v].to_s.split(',')
    psa = params[:psa].to_s.split(',')
    logp = params[:logp].to_s.split(',')
    rb = params[:rb].to_s.split(',')
    lr = params[:lr].to_s.split(',')
    cond = ["mass>=? and mass<=? and volume>=? and volume<=? and asa_p>=? and asa_p<=? and logp>=? and logp<=? and b_rotn>=? and b_rotn<=? and lip_violation>=? and lip_violation<=?",m[0],m[1],v[0],v[1],psa[0],psa[1],logp[0],logp[1],rb[0],rb[1],lr[0],lr[1]]
    if request.xhr?
      @results = Compound.where(cond).count
    else
      if params[:m].present?
        @results = Compound.where(cond)
      end
    end
  end
end
